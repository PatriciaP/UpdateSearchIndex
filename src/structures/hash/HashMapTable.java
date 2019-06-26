package structures.hash;

import structures.list.List;

import java.util.Arrays;

public class HashMapTable {

    private int tableSize;
    private HashMapCell[] hashTable;
    private int[] weight;


    public HashMapTable(int tableSize) {
        this.tableSize = tableSize;
        this.hashTable = new HashMapCell[this.tableSize];
        //initializing
        for (int i = 0; i < this.tableSize; i++) {
            this.hashTable[i] = null;
        }
        this.weight = HashMapUtil.generateWeights(tableSize);
    }


    /**
     *  Insertion should reuse deleted slots when possible
     * @param key
     * @param line
     * @param option
     */
    public void add(String key, int line, int option) {

        int h = HashMapUtil.h(key.toLowerCase(), this.weight, this.tableSize);
        int h2 = HashMapUtil.h2(key.toLowerCase(), this.weight, this.tableSize);
        int initialHash = -1;
        int indexDeletedCell = -1;
        int constant = 0;

        while (h != initialHash
                && hashTable[h] == HashMapCell.Deleted.getUniqueDeleted()
                || hashTable[h] != null && hashTable[h].getKeyWord().compareToIgnoreCase(key) !=0) {

            if (initialHash == -1) {
                initialHash = h;
            }

            if (hashTable[h] == HashMapCell.Deleted.getUniqueDeleted()) {
                indexDeletedCell = h;
            }

            switch (option) {
                // OPEN HASH LINEAR PROBING
                // distance between probes is constant
                case 0:
                    h = (h + 1) % this.tableSize;
                    break;
                // OPEN HASH QUADRATIC PROBING
                // distance between probes increases by certain constant at each step
                case 1:
                    h = (h + (++constant)) % this.tableSize;
                    break;
                // OPEN HASH DOUBLE HASHING
                // distance between probes is calculated using another hash function
                case 2:
                    h = (h + h2) % this.tableSize;
            }

        }

        if ((hashTable[h] == null ||h == initialHash ) && indexDeletedCell != -1) {
            List list = new List();
            list.add(line);
            hashTable[indexDeletedCell] = new HashMapCell(key, list, false);
        } else if (initialHash != h) {
            if (hashTable[h] != HashMapCell.Deleted.getUniqueDeleted()

                    && hashTable[h] != null && hashTable[h].getKeyWord().compareToIgnoreCase(key) == 0)

                //update the line number in the keyword
                hashTable[h].setLines(line);

            else {

                List list2 = new List();
                list2.add(line);
                hashTable[h] = new HashMapCell(key, list2, false);

            }
        }
    }


    @Override
    public String toString() {
        return "HashMapTable{" +
                "tableSize=" + tableSize +
                ", hashTable=" + Arrays.toString(hashTable) +
                ", weight=" + Arrays.toString(weight) +
                '}';
    }
}