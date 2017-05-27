package fr.ladn.carsharingclub.ing1.controller;

import fr.ladn.carsharingclub.ing1.model.OperationMock;
import fr.ladn.carsharingclub.ing1.model.Priority;
// import fr.ladn.carsharingclub.ing1.model.Vehicle;
// import fr.ladn.carsharingclub.ing1.sockets.Server;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Starts the server
 */
public class Starter {
    public static void main(String[] args) {

        SortedSet<OperationMock> set = new TreeSet<>((OperationMock o1, OperationMock o2) -> {
            if (o1.getPriority().getValue() == o2.getPriority().getValue()) {
                return Double.compare(o1.getEstimatedDuration(), o2.getEstimatedDuration());
            } else {
                return Integer.compare(o2.getPriority().getValue(), o1.getPriority().getValue());
            }
        });

        OperationMock o1 = new OperationMock(1, "Joint de culasse", 123, Priority.CRITICAL, 2.5);
        OperationMock o2 = new OperationMock(2, "Fuite réservoir", 456, Priority.URGENT, 3);
        OperationMock o3 = new OperationMock(3, "Déformation carrosserie", 789, Priority.MINOR, 2);
        OperationMock o4 = new OperationMock(4, "Fuite moteur", 101, Priority.CRITICAL, 3);

        set.add(o1);
        set.add(o2);
        set.add(o3);
        set.add(o4);

        System.out.println("\n");
        for (OperationMock operationMock : set) {
            System.out.println(operationMock.toString());
        }
        System.out.println("\n");

        // new Server();
    }
}