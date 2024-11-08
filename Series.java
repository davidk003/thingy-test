public class Series <T> {

     private LL<T> seriesData;
     /**
      * Constructs a new Series object.
      *
      * @param _rowNames an array of row names
      * @param _data     an array of Integer data
      */
     public Series(String[] _rowNames, T[] _data) throws NullPointerException, IllegalArgumentException {
          if (_data == null) {
               throw new NullPointerException("Series(String[] _index, T[] " +
                       "_data): _data can't be null. Terminating the program");
          }
          try {
               if (_rowNames.length != _data.length) {
                    throw new IllegalArgumentException("Series(String[] _index, T[] _data):" +
                            " the length of _index and _data must be the same");
               } else {
                    for(String rowName : _rowNames)
                    {
                         if (rowName == null || rowName.equals(""))
                         {
                              throw new IllegalArgumentException("Series(String[] _index, T[] _data):"+
                                      " _rowNames is not valid");
                         }
                    }

                    seriesData = new LL<T>();
                    for (int i = 0; i < _rowNames.length; i++) {
                         seriesData.appendNode(_rowNames[i], _data[i]);
                    }
               }
          } catch (NullPointerException e) {
               seriesData = new LL<T>();
               for (int i = 0; i < _data.length; i++) {
                    seriesData.appendNode(Integer.toString(i), _data[i]);
               }
          }
     }

     /**
      * Returns a string representation of the Series object.
      */
     public String toString() {
          return seriesData.toString();
     }

     /**
      * Returns the length of the series object.
      */
     public int getLength() {
          return seriesData.getLength();
     }

     /**
      * Returns the row names of this Series object.
      */
     public String[] getRowNames() {
          return seriesData.getIndexArray();
     }

     /**
      * Returns the data of this Series object as strings.
      */
     public String[] getData() {
          return seriesData.getDataArray();
     }

     /**
      * Adds a new pair of rowName and data at the end of the Series object.
      *
      * @param rn the row name to be added
      * @param d  the Integer data value to be added
      */
     public void append(String rn, T d) {
          seriesData.appendNode(rn, d);
     }

     /**
      * Retrieves a data value given a row name.
      *
      * @param rn the row name to search for
      */
     public T loc(String rn) throws NullPointerException, IllegalArgumentException {
          // Loop thru rowNames
          if (rn == null) {
               throw new NullPointerException("loc(String rn): rn can't be null");
          } else if (rn.isEmpty()) {
               throw new IllegalArgumentException("loc(String rn): rn can't be an empty string");
          }

          LL<T>.LLNode get = seriesData.searchNode(rn);
          if (get == null) {
               return null;
          }
          else {
               return get.getData();
          }
          //If entire rowNames is iterated, it doesnt exist.
     }

     /**
      * Retrieves multiple data values given an array of row names.
      *
      * @param rn an array of row names to search for
      */
     public T[] loc(String[] rn) throws NullPointerException, IllegalArgumentException {
          if (rn == null) {
               throw new NullPointerException("loc(String[] rn): rn[] can't be null");
          } else if (rn.length == 0) {
               throw new IllegalArgumentException("loc(String[] rn): rn[] can't be an empty array");
          }
          T[] returnLoc = (T[]) new Object[rn.length];
          for (int i = 0; i < rn.length; i++) {
               try {
                    returnLoc[i] = loc(rn[i]);
                    System.out.println(returnLoc[i].getClass());

               } catch (Exception e) {
                    returnLoc[i] = null;
               }
          }
          System.out.println(returnLoc.getClass());
          return returnLoc;
     }

     /**
      * Retrieves a data value based on its integer index.
      *
      * @param ind the index of the data to retrieve
      */
     public T iloc(int ind) {
          try {
               String[] arr = seriesData.getIndexArray();
               String x = arr[ind];
               return loc(x);
          } catch (IndexOutOfBoundsException e) {
               System.out.println("the index " + ind + " is not valid.. returning null");
               return null;
          }
     }

     /**
      * Removes a pair of rowname-data from the Series, given a row name.
      *
      * @param rn the row name of the pair to be removed
      */
     public boolean drop(String rn) throws NullPointerException, IllegalArgumentException {
          if (rn == null) {
               throw new NullPointerException("drop(String ind): ind can't be null");
          } else if (rn.isEmpty()) {
               throw new IllegalArgumentException("drop(String ind): ind can't be an empty String");
          }

          if (seriesData.searchNode(rn) != null) {
               seriesData.removeNode(rn);
               return true;
          }
          return false; //Row name not found return false
     }

     /**
      * Replace any data value that is null with value.
      *
      * @param value the new value to replace null values
      */
     public void fillNull(T value) throws IllegalArgumentException {
          if (value == null) {
               throw new IllegalArgumentException("fillNull(T value): value can't be null");
          }

          String[] indexs = seriesData.getIndexArray();
          for (int i = 0; i < indexs.length; i++) {
               LL<T>.LLNode node = seriesData.searchNode(indexs[i]);
               if (node.getData() == null) {
                    node.setData(value);
               }
          }
     }
}
