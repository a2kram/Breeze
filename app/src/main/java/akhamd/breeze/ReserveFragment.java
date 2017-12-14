package akhamd.breeze;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

/**
 * Reserve fragment containing a simple view
 */
public class ReserveFragment extends Fragment
{
    Button ReserveButton;
    EditText ReservationText;
    EditText ReservationNum;
    RelativeLayout ReservationLayoutFailure;
    RelativeLayout ReservationLayoutSuccess;

    private Restaurant mRestaurant = null;

    public ReserveFragment() {}

    public static ReserveFragment newInstance()
    {
        return new ReserveFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_reserve_screen, container, false);

        super.onCreate(savedInstanceState);

        ReserveButton = (Button)rootView.findViewById(R.id.reservation_button);
        ReservationText = (EditText)rootView.findViewById(R.id.reservation_requests);
        ReservationNum = (EditText) rootView.findViewById(R.id.reservation_num);
        ReservationLayoutFailure = (RelativeLayout)rootView.findViewById(R.id.reservation_layout_failure);
        ReservationLayoutSuccess = (RelativeLayout)rootView.findViewById(R.id.reservation_layout_success);

        if(mRestaurant.isAcceptsReservations())
        {
            ReservationLayoutSuccess.setVisibility(View.VISIBLE);
            ReservationLayoutFailure.setVisibility(View.INVISIBLE);
        }
        else
        {
            ReservationLayoutSuccess.setVisibility(View.INVISIBLE);
            ReservationLayoutFailure.setVisibility(View.VISIBLE);
        }

        ReserveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
           // Toast.makeText(v.getContext().getApplicationContext(), "Reserving for ", ReservationNum.getValue(),
             //       Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }
}