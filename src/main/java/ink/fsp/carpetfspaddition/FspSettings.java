package ink.fsp.carpetfspaddition;


import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

public class FspSettings {
    public static final String fsp = "FSP";
    public enum broadcastCarpetCommandFeedbackEnum {
        ALL,
        SELF
    }

    @Rule(categories = {fsp, RuleCategory.SURVIVAL})
    public static Boolean itemFrameAdvancedInteract = false;
    @Rule(categories = {fsp, RuleCategory.SURVIVAL})
    public static boolean playerSit = false;
    @Rule(categories = {fsp})
    public static broadcastCarpetCommandFeedbackEnum broadcastCarpetCommandFeedback = broadcastCarpetCommandFeedbackEnum.ALL;
}
