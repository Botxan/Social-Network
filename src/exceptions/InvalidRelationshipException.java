/**
 * InvalidRelationshipException represents the situation in which the relation inside network is invalid
 * for one or more reasons.
 */
package exceptions;

@SuppressWarnings("serial")
public class InvalidRelationshipException extends RuntimeException
{
   /**
    * Sets up this exception with an appropriate message.
    */
   public InvalidRelationshipException (String reason) {
      super ("The relation is invalid. Reason: " + reason);
   }
}