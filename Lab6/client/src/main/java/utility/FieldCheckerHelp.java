package utility;

import exceptions.IncorrectValueException;
import exceptions.NullFieldException;
import exceptions.ValidationException;
import exceptions.WrongInputFormatException;

public interface FieldCheckerHelp<T> {
    T check(String str) throws NullFieldException, IncorrectValueException, WrongInputFormatException;
}
