package no.bouvet.p2pcommunication.util.button;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import no.bouvet.p2pcommunication.R;
import no.bouvet.p2pcommunication.listener.wifip2p.WifiP2pListener;

public class MapButton extends Button {

    private WifiP2pListener wifiP2pListener;
    private DiscoveryAndConnectionButtonState buttonState;

    public MapButton(Context context) {
        super(context);
    }

    public MapButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MapButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initialize() {

       this.setText("position");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
    }

    @OnClick
    public void onClick() {
        TextView registerLink;


    }



}
