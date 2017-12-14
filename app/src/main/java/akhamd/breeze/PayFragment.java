package akhamd.breeze;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Pay fragment containing a simple view
 */
public class PayFragment extends Fragment
{
    Button PayButton;
    TextView PayText;
    RelativeLayout PaymentLayoutFailure;
    RelativeLayout PaymentLayoutSuccess;

    private Restaurant mRestaurant = null;

    public PayFragment() {}

    public static PayFragment newInstance()
    {
        return new PayFragment();
    }

    public void setRestaurant(Restaurant restaurant)
    {
        mRestaurant = restaurant;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_pay_screen, container, false);

        super.onCreate(savedInstanceState);

        PayButton = (Button)rootView.findViewById(R.id.payment_button);
        PayText = (TextView)rootView.findViewById(R.id.payment_details);
        PaymentLayoutFailure = (RelativeLayout)rootView.findViewById(R.id.payment_layout_failure);
        PaymentLayoutSuccess = (RelativeLayout)rootView.findViewById(R.id.payment_layout_success);

        if(mRestaurant.isAcceptsReservations())
        {
            PaymentLayoutSuccess.setVisibility(View.VISIBLE);
            PaymentLayoutFailure.setVisibility(View.INVISIBLE);
        }
        else
        {
            PaymentLayoutSuccess.setVisibility(View.INVISIBLE);
            PaymentLayoutFailure.setVisibility(View.VISIBLE);
        }

        PayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Toast.makeText(v.getContext().getApplicationContext(), "Reserving for ", ReservationNum.getValue(),
                //       Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }
}