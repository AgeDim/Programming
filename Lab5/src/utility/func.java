package utility;

import exceptions.WrongInputFormatException;

public interface func<T> {
    T func(String str) throws WrongInputFormatException;
}
