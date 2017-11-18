package com.upshift.upmap.upmap.Modules;

import java.util.List;


/**
 * Created by eliud on 11/28/16.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}