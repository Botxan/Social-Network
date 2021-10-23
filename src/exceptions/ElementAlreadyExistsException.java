/**
 * ElementNotFoundException represents the situation in which a target element 
 * os already in the target data structure
 */
package exceptions;

@SuppressWarnings("serial")
public class ElementAlreadyExistsException extends RuntimeException
{
   /**
    * Sets up this exception with an appropriate message.
    */
   public ElementAlreadyExistsException (String collection) {
      super ("The target is already inside " + collection);
   }
}