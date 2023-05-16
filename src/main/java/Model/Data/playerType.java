package Model.Data;

public class playerType {
    static int id;

        public IPlayer playerType(String type)
    {
        //add if statment if host already been created gey out the host select option
        //if there isnt a host grey out guest option
        id=0;
        if(id<3){
        if (type=="guest")
            return new Guest(++id);
        return new Host();
        }
        return null;
    }

    }



