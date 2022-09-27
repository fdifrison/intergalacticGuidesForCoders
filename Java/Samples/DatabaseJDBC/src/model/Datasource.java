package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Datasource {

	private static final String DB_NAME = "music.db";
	private static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

	private static final String TABLE_ALBUMS = "albums";
	private static final String COLUMN_ALBUM_ID = "_id";
	private static final String COLUMN_ALBUM_NAME = "name";
	private static final String COLUMN_ALBUM_ARTIST = "artist";

	private static final String TABLE_ARTIST = "artists";
	private static final String COLUMN_ARTIST_ID = "_id";
	private static final String COLUMN_ARTIST_NAME = "name";

	private static final String TABLE_SONGS = "songs";
	private static final String COLUMN_SONG_ID = "_id";
	private static final String COLUMN_SONG_TRACK = "track";
	private static final String COLUMN_SONG_TITLE = "title";
	private static final String COLUMN_SONG_ALBUM = "album";
	
	public enum sortStyle {
		ID,
		DESC,
		ASC,	
	}

	private Connection connection;

	public boolean open() {
		try {
			connection = DriverManager.getConnection(CONNECTION_STRING);
			System.out.println(MessageFormat.format("Connected to {0}", DB_NAME));
			return true;
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			return false;
		}
	}

	public void close() {
		try {
			if (connection != null) {
				System.out.println(MessageFormat.format("Disconnecting from {0}", DB_NAME));
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Failed to close connection");
			e.printStackTrace();
		}
	}

	public List<Artist> queryArtists(sortStyle sort) {
		
		String query = sort != sortStyle.ID ? // ID is default ordering
				MessageFormat.format("SELECT * FROM {0} ORDER BY {1} {2}", TABLE_ARTIST, COLUMN_ARTIST_NAME , sort) :
				MessageFormat.format("SELECT * FROM {0}", TABLE_ARTIST);
		
		System.out.println(query);
		
		try (Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query)) {

			List<Artist> artists = new ArrayList<>();
			while (resultSet.next()) {
				Artist artist = new Artist();
				artist.setId(resultSet.getInt(COLUMN_ARTIST_ID));
				artist.setName(resultSet.getString(COLUMN_ARTIST_NAME));
				artists.add(artist);
			}
			
			return artists;

		} catch (SQLException e) {
			System.out.println(MessageFormat.format("Query failed: {0}", e.getMessage()));
			e.printStackTrace();
			return null;

		}
	}
}