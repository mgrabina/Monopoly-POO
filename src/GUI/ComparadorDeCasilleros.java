package GUI;


import java.util.Comparator;
/**
 * 
 * @author MartinGrabina
 *
 * @param <T>
 */
public class ComparadorDeCasilleros<T> implements Comparator<MateriaView> {
	public int compare(MateriaView x, MateriaView y){
		if(x.id < y.id)
			return -1;
		if(x.id > y.id)
			return 1;
		return 0;			
	}
}
