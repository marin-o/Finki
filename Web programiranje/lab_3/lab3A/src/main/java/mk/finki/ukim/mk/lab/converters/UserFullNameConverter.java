package mk.finki.ukim.mk.lab.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mk.finki.ukim.mk.lab.model.UserFullName;

@Converter
public class UserFullNameConverter implements AttributeConverter<UserFullName, String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(UserFullName fullName){
        if (fullName == null)
            return null;

        StringBuilder sb = new StringBuilder();
        if(fullName.getSurname() != null && !fullName.getSurname().isEmpty()){
            sb.append(fullName.getSurname());
        }

        if (fullName.getName() != null
                && !fullName.getName().isEmpty()) {
            sb.append(SEPARATOR);
            sb.append(fullName.getName());
        }

        return sb.toString();
    }

    @Override
    public UserFullName convertToEntityAttribute(String dbUserFullName) {
        if (dbUserFullName == null || dbUserFullName.isEmpty()) {
            return null;
        }

        String[] pieces = dbUserFullName.split(SEPARATOR);

        if (pieces.length == 0) {
            return null;
        }

        UserFullName fullName = new UserFullName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbUserFullName.contains(SEPARATOR)) {
            fullName.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                fullName.setName(pieces[1]);
            }
        } else {
            fullName.setName(firstPiece);
        }

        return fullName;
    }

}
