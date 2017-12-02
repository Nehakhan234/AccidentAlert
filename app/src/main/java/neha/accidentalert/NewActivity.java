package neha.accidentalert;

/**
 * Created by Neha on 11/28/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;


public class NewActivity extends AppCompatActivity {
    EditText Name, Pass , delete;
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        delete = (EditText) findViewById(R.id.editText6);

        helper = new myDbAdapter(this);
    }


    public void addUser(View view) {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();

        if (t1.isEmpty() || t2.isEmpty())
        {
            Message.message(getApplicationContext(), "Enter Both Name and Phone no.");
        }
        else
        {

            if (isValidPhone(Pass.getText().toString()))
            {

                Message.message(getApplicationContext(), "Valid Phone no.");
                long id = helper.insertData(t1, t2);
                if (id <= 0)
                {
                    Message.message(getApplicationContext(), "Insertion Unsuccessful");
                    Name.setText("");
                    Pass.setText("");
                } else
                {
                    Message.message(getApplicationContext(), "Insertion Successful");
                    Name.setText("");
                    Pass.setText("");
                }
            }
            else
            {

                Message.message(getApplicationContext(), "Enter Valid Phone no.");

            }
        }
    }

   /* public boolean isValidPhone(CharSequence phone) {
        if (TextUtils.isEmpty(phone))
        {
        return false;
        } else
        {
        return android.util.Patterns.PHONE.matcher(phone).matches();
        }
        }
*/
    private boolean isValidPhone(String phone)
    {
        boolean check=false;

            if(phone.length() < 10 || phone.length() > 10)
            {
                check = false;

            }
            else
            {
                check = true;

            }


        return check;
    }


    public void viewdata(View view)
    {
        String data = helper.getData();
        Message.message(this,data);
    }


   public void delete( View view)
    {
        String uname = delete.getText().toString();
        if(uname.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Data");
        }
        else{
            int a= helper.delete(uname);
            if(a<=0)
            {
                Message.message(getApplicationContext(),"Unsuccessful, Enter correct Name");
                delete.setText("");
            }
            else
            {
                Message.message(this, "Deleted Successfully");
                delete.setText("");
            }
        }
    }
}
