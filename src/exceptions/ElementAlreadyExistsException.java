package exceptions;

/**
 * ElementNotFoundException represents the situation in which the target element 
 * is already in the target data structure
 * @author Oihan and Eneko
 *
 */
@SuppressWarnings("serial")
public class ElementAlreadyExistsException extends RuntimeException
{
   /**
    * Sets up this exception with an appropriate message.
    * @param collection the collection
    */
   public ElementAlreadyExistsException (String collection) {
      super ("The target is already inside " + collection);
   }
}