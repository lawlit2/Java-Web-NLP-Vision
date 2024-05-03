package com.example.Util;

import netscape.javascript.JSObject;
import org.json.JSONObject;

public interface AirOcr {
    JSONObject OCRIdCard(String path);
    JSONObject DriverLicenceOcr(String path);
}
