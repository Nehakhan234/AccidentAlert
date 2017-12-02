package neha.accidentalert;


        import android.content.Intent;
        import android.location.Location;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.telephony.SmsManager;
        import android.view.View;
        import android.view.Window;
        import android.widget.Button;
        import android.widget.Toast;

        import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second_activity);

        helper = new myDbAdapter(this);

    }

    public void sendloc(View view) {
                GPStracker g = new GPStracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    String locmessage = "HELP!!! I have met with an accident, Please follow my location http://maps.google.com/maps?saddr=" + lat + "," + lon;
                    ArrayList<String> phno = new ArrayList<>();
                    phno = helper.sendSms();

                    for (int j = 0; j < phno.size(); j++)
                    {
                        System.out.println(phno.get(j));
                       sendSMS(phno.get(j), locmessage);
                    }
                }
            }

    public void send (View view) {

        String message = "HELP!!! Hi there, I have met with an  accident, It's an emergency... Please reach out to me as soon as possible!";
        ArrayList<String> pno = new ArrayList<>();
        pno = helper.sendSms();

        for (int i = 0; i < pno.size(); i++)
        {
            System.out.println(pno.get(i));
            sendSMS(pno.get(i), message);

        }
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);


        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();

    }

 }




