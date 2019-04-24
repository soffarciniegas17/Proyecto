package com.example.sophie.proyecto;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Dialog error_message;
    private EditText user, pass;
    private CheckBox check;
    private final String USUARIO="user1234";
    private final String CLAVE="ecom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user= findViewById(R.id.usuario_main);
        pass= findViewById(R.id.pass_id);
        check= findViewById(R.id.check_main);

        error_message= new Dialog(this);
        error_message.setContentView(R.layout.error_dialog);


        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    public void login(View view) {
        String usuario = user.getText().toString();
        String contraseña= pass.getText().toString();

        if(contraseña.equals("") && usuario.equals("")){
            errorMessage(0);
        }else if(usuario.equals("")){
            errorMessage(3);
        }else if(contraseña.equals("")){
            errorMessage(4);
        }else if(usuario.equals(USUARIO) && contraseña.equals(CLAVE)){
            startActivity(new Intent(this, Perfil.class));
            finish();
        }else if(usuario.equals(USUARIO)){
            errorMessage(1);
        }else if(contraseña.equals(CLAVE)){
            errorMessage(2);
        }else {
            errorMessage(10);
        }

    }

    @SuppressLint("ResourceAsColor")
    private void errorMessage(int e) {
        TextView texto = error_message.findViewById(R.id.mensaje_error);

            switch (e){
                case 1:
                    texto.setText("Error. La contraseña es incorrecta. Intente de nuevo. ");
                    pass.setHintTextColor((R.color.red));
                    pass.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);

                    error_message.show();
                    break;
                case 2:
                    texto.setText("Error. El usuario es incorrecto. Intente de nuevo. ");
                    user.setHintTextColor(R.color.red);
                    user.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);

                    error_message.show();
                    break;
                case 3:
                    user.setError("Campo vacio");
                    break;
                case 4:
                    pass.setError("Campo vacio");
                    break;

                case 0:
                    user.setError("Campo vacio");
                    pass.setError("Campo vacio");
                    break;
                         default:
                            texto.setText("Error. La información es incorrecta. Intente de nuevo");
                             error_message.show();
                        break;
            }

         Button out = error_message.findViewById(R.id.cerrar_error);

            out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    error_message.dismiss();

                }
            });



    }
}
