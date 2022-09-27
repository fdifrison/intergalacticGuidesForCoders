package main;

import java.text.MessageFormat;
import java.util.List;

import javax.naming.ldap.SortControl;

import model.Artist;
import model.Datasource;
import model.Datasource.sortStyle;

public class main {
	public static void main(String[] args) {
		
		Datasource datasource = new Datasource();
		datasource.open();
		
		List<Artist> artists = datasource.queryArtists(sortStyle.ID);
		artists.stream().forEach((i) -> System.out.println(MessageFormat.format("id:{0} - {1}", i.getId(), i.getName() )));

		datasource.close();
	}
}
