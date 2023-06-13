package projet;

import java.util.Comparator;

public class c implements Comparator {
	public int compare(Object a1,Object a2){
		if(a1 instanceof Article_electromenager && a2 instanceof Article_electromenager) {
			return ((Article_electromenager)a1).getStock()-((Article_electromenager)a2).getStock();
		}
		if(a1 instanceof Article_electromenager && a2 instanceof Primeur) {
			return (int) (((Article_electromenager)a1).getStock()-((Primeur)a2).getStock());
		}
		if(a1 instanceof Primeur && a2 instanceof Article_electromenager) {
			return (int) (((Primeur)a1).getStock()-((Article_electromenager)a2).getStock());
		}
		else {
			return (int) (((Primeur)a1).getStock()-((Primeur)a2).getStock());
		}
	}
}
