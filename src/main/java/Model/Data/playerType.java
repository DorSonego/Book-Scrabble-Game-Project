package Model.Data;

public class playerType {
    static int id;

        public IPlayer playerType(String type)
    {
        id=0;
        if (type=="guest")
            return new Guest(++id);
        return new Host();

    }

    }



