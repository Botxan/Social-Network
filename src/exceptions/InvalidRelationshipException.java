package exceptions;

/**
 * InvalidRelationshipException represents the situation in which the relation inside the network is invalid
 * for one or more reasons.
 * @author Oihan and Eneko
 */
@SuppressWarnings("serial")
public class InvalidRelationshipException extends RuntimeException
{
   /**
    * Sets up this exception with an appropriate message.
    * @param reason the reason why the relation is invalid
    */
   public InvalidRelationshipException (String reason) {
      super ("The relation is invalid. Reason: " + reason);
   }
}