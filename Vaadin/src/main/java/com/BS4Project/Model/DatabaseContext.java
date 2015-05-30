package com.BS4Project.Model;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.vaadin.ui.Notification;

import java.net.UnknownHostException;

/**
 * Created by Peter on 21-5-2015.
 */
public class DatabaseContext {

        static MongoClient mongo;
        static DB db;
        static DBCollection collection;

        /**
         * todo: JavaDocs
         *
         */
        public DatabaseContext(){
            //System.out.println("DatabaseContext wordt aangeroepen");
            try {
                connectDB();
            } catch (UnknownHostException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }

        /**
         * ConnectDB will connect the GUI with the MongoClient to get data out of the database
         *
         * @throws UnknownHostException
         */
        private void connectDB() throws UnknownHostException{
            mongo = new MongoClient();
            db = mongo.getDB("test");
            collection = db.getCollection("Producten");
        }

        /**
         *
         *
         * This function disconnect or closes the connection with Database.
         */
        public void disconnectDB(){
            mongo.close();
        }

        /**
         *
         *
         * @param  : basicDBObject must contain the object name in the database
         */
        public DBCursor getDataCursor(){
            DBCursor cursor = null;
            if (mongo != null) {
                //Get all
                cursor = collection.find();
            }
            return cursor;
        }


}
