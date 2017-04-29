package boardClasses;

import resourceClasses.ResourceType;

/**
 * Harbor class for marine trade
 * Only one field, the ResourceType that indicates the trade type.
 * If the ResourceType is a desert then it is a ANY 3:1 port,
 * If the ResourceType is not a desert then it is a 2:1 port for that resource type
 *
 */
public class Harbor {
    ResourceType tradeType;
    public Harbor(ResourceType tradeType){
        this.tradeType = tradeType;
    }

    public ResourceType getTradeType(){
        return tradeType;
    }
}
