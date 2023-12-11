package com.example.studyshare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class AddFilesMisArchivos extends AppCompatActivity {
    EditText name, description;
    Button upload, cancel, agregarPDF;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_files_mis_archivos);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        name = findViewById(R.id.agregarNombre);
        description = findViewById(R.id.agregarDescripcion);
        agregarPDF = findViewById(R.id.agregarPDF);
        upload = findViewById(R.id.AgregarArchivosMisArchivos);
        cancel = findViewById(R.id.CancelarAgregarMisArchivos);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserData();
                cerrar();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        agregarPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, 1);
            }
        });
    }

    private void inserData() {
        // Genera un identificador único
        String uniqueId = FirebaseDatabase.getInstance().getReference().push().getKey();

        // Crea un mapa con los datos
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", name.getText().toString());
        map.put("descripcion", description.getText().toString());
        map.put("archivoId", uniqueId); // Agrega el identificador único a los datos

        // Guarda los datos en Realtime Database
        FirebaseDatabase.getInstance().getReference().child("misarchivos").child(uniqueId)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddFilesMisArchivos.this, "Datos agregados exitosamente", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddFilesMisArchivos.this, "Error al agregar datos, intentelo más tarde", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void cerrar() {
        name.setText("");
        description.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri fileUri = data.getData();
            SubirAchivosAFirebase(fileUri);
        }
    }

    private void SubirAchivosAFirebase(Uri fileUri) {
        // Genera un identificador único para el archivo PDF
        String pdfId = System.currentTimeMillis() + "";

        // Crea la referencia en Firebase Storage con el identificador único
        StorageReference fileRef = storageRef.child("archivosPDF/" + pdfId + ".pdf");

        // Agrega el identificador único como un campo en los metadatos del archivo
        fileRef.getMetadata()
                .addOnSuccessListener(storageMetadata -> {
                    storageMetadata.getCustomMetadata("pdfId");
                })
                .addOnFailureListener(exception -> {
                    fileRef.updateMetadata(new StorageMetadata.Builder()
                            .setCustomMetadata("pdfId", pdfId)
                            .build());
                });

        // Sube el archivo PDF
        fileRef.putFile(fileUri)
                .addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(AddFilesMisArchivos.this, "Archivo PDF subido exitosamente", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AddFilesMisArchivos.this, "Error al subir el archivo PDF", Toast.LENGTH_SHORT).show();
                });
    }
}
