package com.example.vullfugir;

import android.app.Activity;
import android.graphics.Typeface;

public class GeneralActivity extends Activity {
	public static final String PREFERENCIES = "GamePrefs";
    public static final String JOC_RECORD = "record";
    public static final String JOC_NOMRECORD = "nomrecord";
    
    protected Typeface fontJoc;
    
    public void init()
    {
    	fontJoc = Typeface.createFromAsset(this.getAssets(), "fonts/arkitechbold.ttf");
    }
}
