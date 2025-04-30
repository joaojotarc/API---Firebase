package br.com.firebase.api.repositorio;

import br.com.firebase.api.dao.Usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class UsuarioRepositorio {

    private static final String COLECAO = "usuarios";

    public String salvar(Usuario usuario) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLECAO).document();
        usuario.setId(docRef.getId());
        ApiFuture<WriteResult> future = docRef.set(usuario);
        try {
            future.get();
            return usuario.getId();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> listarTodos() {
        Firestore db = FirestoreClient.getFirestore();
        List<Usuario> usuarios = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection(COLECAO).get();
        try {
            for (DocumentSnapshot doc : future.get().getDocuments()) {
                Usuario usuario = doc.toObject(Usuario.class);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario buscarPorId(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLECAO).document(id);
        try {
            DocumentSnapshot doc = docRef.get().get();
            return doc.exists() ? doc.toObject(Usuario.class) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizar(Usuario usuario) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLECAO).document(usuario.getId()).set(usuario);
    }

    public void deletar(String id) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLECAO).document(id).delete();
       
    }
}
