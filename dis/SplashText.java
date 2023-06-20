package inkandsoul.splash;

import java.util.ArrayList;
import java.util.List;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;

public interface SplashText {
    public static final Event<SplashText> EVENT = EventFactory.createEventResult(SplashText.class);

    public static List<String> text = new ArrayList<>();

    public static void addText(String t){
        text.add(t);
    }

    String splashText();
}
