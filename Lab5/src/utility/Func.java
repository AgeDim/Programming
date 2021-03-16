package utility;

import exceptions.WrongInputFormatException;

public interface Func<T> {
    T Func(String str) throws WrongInputFormatException;
}
