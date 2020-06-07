package entities;

public interface PorterInterface {

    /**
     *  Verify if there are bags in the plane hold.
     *
     *  @return True, if the Plane Hold is empty. False, if the Plane Hold is not empty.
     */
    boolean isPlaneHoldEmpty();
    /**
     *  Set the value of the plane hold.
     *
     *  @param planeHoldEmpty True, if the Plane Hold is empty or to false, if the Plane Hold is not empty.
     */
    void setPlaneHoldEmpty(boolean planeHoldEmpty);
}
