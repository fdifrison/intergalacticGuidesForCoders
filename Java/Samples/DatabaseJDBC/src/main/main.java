package main;

import java.text.MessageFormat;
import java.util.List;

import model.Artist;
import model.Datasource;
import model.Datasource.sortStyle;
import model.Datasource.table;
import model.SongArtist;

public class main {
	public static void main(String[] args) {
		
		Datasource datasource = new Datasource();
		datasource.open();
		
		List<Artist> artists = datasource.queryArtists(sortStyle.ID);
		artists.stream().forEach((i) -> System.out.println(MessageFormat.format("id:{0} - {1}", i.getId(), i.getName() )));
		
		List<String> albumForArtiStrings = datasource.queryAlbumsForArtist("Iron Maiden", sortStyle.ASC);
		albumForArtiStrings.forEach(System.out::println);
		
//		List<SongArtist> songArtists = datasource.querySongArtists("Go Your Own Way' or 1=1 or '", sortStyle.ASC);
		List<SongArtist> songArtists = datasource.querySongArtists("Go Your Own Way", sortStyle.ASC);
		songArtists.stream().forEach((i) -> System.out.println(MessageFormat.format("{0} - {1} - {2}", i.getArtistName(), i.getAlbumName(), i.getTrack()) ));

		datasource.getMetadata(table.albums.name());
		
		datasource.insertSong("Like a 70's Band", "Rolling Onions", "In the Age of Rock LP", 1);
		
		datasource.close();
	}
}
