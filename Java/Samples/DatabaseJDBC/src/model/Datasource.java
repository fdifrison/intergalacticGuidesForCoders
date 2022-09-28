package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

	// Class like data source are usually defined as SINGLETON, meaning that there
	// can be only one instance active
	// of this class in order not

	private static final String DB_NAME = "music.db";
	private static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

	private static final String TABLE_ALBUMS = table.albums.name();
	private static final String COLUMN_ALBUM_ID = "_id";
	private static final String COLUMN_ALBUM_NAME = "name";
	private static final String COLUMN_ALBUM_ARTIST = "artist";

	private static final String TABLE_ARTIST = table.artists.name();
	private static final String COLUMN_ARTIST_ID = "_id";
	private static final String COLUMN_ARTIST_NAME = "name";

	private static final String TABLE_SONGS = table.songs.name();
	private static final String COLUMN_SONG_ID = "_id";
	private static final String COLUMN_SONG_TRACK = "track";
	private static final String COLUMN_SONG_TITLE = "title";
	private static final String COLUMN_SONG_ALBUM = "album";

	public enum table {
		albums, artists, songs
	}

	public enum sortStyle {
		ID, DESC, ASC,
	}

	private PreparedStatement querySecured = null;
	private PreparedStatement putSecured = null;

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

			if (querySecured != null) {
				querySecured.close();
			}
		} catch (SQLException e) {
			System.out.println("Failed to close connection");
			e.printStackTrace();
		}
	}

	public List<Artist> queryArtists(sortStyle sortby) {

		String query = sortby != sortStyle.ID ? // ID is default ordering
				MessageFormat.format("SELECT * FROM {0} ORDER BY {1} {2}", TABLE_ARTIST, COLUMN_ARTIST_NAME, sortby)
				: MessageFormat.format("SELECT * FROM {0}", TABLE_ARTIST);

		System.out.println(query);

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

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

	public List<String> queryAlbumsForArtist(String artistName, sortStyle sortby) {

		String query = MessageFormat.format(
				"SELECT {0}.{1} FROM {0} INNER JOIN {2} ON {0}.{3} = {2}.{4} WHERE {2}.{5} = ''{6}''", TABLE_ALBUMS,
				COLUMN_ALBUM_NAME, TABLE_ARTIST, COLUMN_ALBUM_ARTIST, COLUMN_ARTIST_ID, COLUMN_ARTIST_NAME, artistName);

		query = (sortby != sortStyle.ID)
				? query.concat(MessageFormat.format(" ORDER BY {0}.{1} COLLATE NOCASE {2}", TABLE_ALBUMS,
						COLUMN_ALBUM_NAME, sortby))
				: query;

		System.out.println(query);

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

			List<String> albums = new ArrayList<>();
			while (resultSet.next()) {
				albums.add(resultSet.getString(1));
			}

			return albums;

		} catch (SQLException e) {
			System.out.println(MessageFormat.format("Query failed: {0}", e.getMessage()));
			e.printStackTrace();
			return null;

		}

	}

	public List<SongArtist> querySongArtists(String title, sortStyle sortby) {

		String query = MessageFormat.format(
				"SELECT {0}.{1}, {2}.{3}, {4}.{5} FROM {4} INNER JOIN {2} ON {4}.{6} = {2}.{7} INNER JOIN {0} ON {2}.{8} = {0}.{9} WHERE {4}.{10} LIKE ?",
				TABLE_ARTIST, COLUMN_ARTIST_NAME, TABLE_ALBUMS, COLUMN_ALBUM_NAME, TABLE_SONGS, COLUMN_SONG_TRACK,
				COLUMN_SONG_ALBUM, COLUMN_ALBUM_ID, COLUMN_ALBUM_ARTIST, COLUMN_ARTIST_ID, COLUMN_SONG_TITLE);

		query = (sortby != sortStyle.ID)
				? query.concat(MessageFormat.format(" ORDER BY {0}.{1}, {2}.{3} COLLATE NOCASE {4}", TABLE_ARTIST,
						COLUMN_ARTIST_NAME, TABLE_ALBUMS, COLUMN_ALBUM_NAME, sortby))
				: query;

		System.out.println(query);

		// HANDLE SQL INJECTION

		try {
			querySecured = connection.prepareStatement(query);
			querySecured.setString(1, title);
		} catch (SQLException e) {
			System.out.println("SQL INJECTION");
			e.printStackTrace();
		}

		try (Statement statement = connection.createStatement(); ResultSet resultSet = querySecured.executeQuery()) {

			List<SongArtist> songArtists = new ArrayList<>();
			while (resultSet.next()) {
				SongArtist artistSongs = new SongArtist();
				artistSongs.setArtistName(resultSet.getString(1));
				artistSongs.setAlbumName(resultSet.getString(2));
				artistSongs.setTrack(resultSet.getInt(3));
				songArtists.add(artistSongs);
			}

			return songArtists;

		} catch (SQLException e) {
			System.out.println(MessageFormat.format("Query failed: {0}", e.getMessage()));
			e.printStackTrace();

			return null;

		}

	}

	public void getMetadata(String table) {
		String query = MessageFormat.format("SELECT * FROM {0}", table);

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

			ResultSetMetaData metaData = resultSet.getMetaData();
			int colNum = metaData.getColumnCount();
			for (int i = 1; i <= colNum; i++) {
				System.out.println(MessageFormat.format("Column {0} in the {1} table is named {2}", i, table,
						metaData.getColumnName(i)));
			}

		} catch (SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private int insertArtist(String name) throws SQLException {

		String query = MessageFormat.format("SELECT {0} FROM {1} WHERE {2} = ?", COLUMN_ARTIST_ID, TABLE_ARTIST,
				COLUMN_ARTIST_NAME);

		String put = MessageFormat.format("INSERT INTO {0} ({1}) VALUES(?)", TABLE_ARTIST, COLUMN_ARTIST_NAME);

		querySecured = connection.prepareStatement(query);
		querySecured.setString(1, name);
		ResultSet resultSet = querySecured.executeQuery();
		if (resultSet.next()) {
			// if the artist is already there return its ID
			return resultSet.getInt(1);
		} else {
			putSecured = connection.prepareStatement(put, Statement.RETURN_GENERATED_KEYS);
			putSecured.setString(1, name);
			int affectedRows = putSecured.executeUpdate();

			if (affectedRows != 1) { // something is wrong we should have affected only one row, throw exception
				throw new SQLException("Coudn't insert artist..");
			}

			// Retrieve the new key of insertion
			ResultSet generatedKey = putSecured.getGeneratedKeys();
			if (generatedKey.next()) {
				return generatedKey.getInt(1);
			} else {
				throw new SQLException("Couldn't retrieve new _id for artist " + name);
			}

		}
	}

	private int insertAlbum(String name, int artistId) throws SQLException {

		String query = MessageFormat.format("SELECT {0} FROM {1} WHERE {2} = ?", COLUMN_ALBUM_ID, TABLE_ALBUMS,
				COLUMN_ALBUM_NAME);

		String put = MessageFormat.format("INSERT INTO {0} ({1}, {2}) VALUES(?, ?)", TABLE_ALBUMS, COLUMN_ALBUM_NAME,
				COLUMN_ALBUM_ARTIST);

		querySecured = connection.prepareStatement(query);
		querySecured.setString(1, name);
		ResultSet resultSet = querySecured.executeQuery();
		if (resultSet.next()) {
			// if the artist is already there return its ID
			return resultSet.getInt(1);
		} else {
			putSecured = connection.prepareStatement(put, Statement.RETURN_GENERATED_KEYS);
			putSecured.setString(1, name);
			putSecured.setInt(2, artistId);
			int affectedRows = putSecured.executeUpdate();

			if (affectedRows != 1) { // something is wrong we should have affected only one row, throw exception
				throw new SQLException("Coudn't insert artist..");
			}

			// Retrieve the new key of insertion
			ResultSet generatedKey = putSecured.getGeneratedKeys();
			if (generatedKey.next()) {
				return generatedKey.getInt(1);
			} else {
				throw new SQLException("Couldn't retrieve new _id for artist " + name);
			}

		}
	}

	// EXAMPLE OF TRANSACTION

	
	public void insertSong(String title, String artist, String album, int track) {

		String put = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}) VALUES(?, ?, ?)", TABLE_SONGS,
				COLUMN_SONG_TRACK, COLUMN_SONG_TITLE, COLUMN_SONG_ALBUM);

		try {
			connection.setAutoCommit(false);

			int artistId = insertArtist(artist);
			int albumId = insertAlbum(album, artistId);
			putSecured = connection.prepareStatement(put);
			putSecured.setInt(1, track);
			putSecured.setString(2, title);
			putSecured.setInt(3, albumId);
			int affectedRows = putSecured.executeUpdate();
			if (affectedRows == 1) {
				connection.commit();
			} else {
				throw new SQLException("The song insert failed");
			}

		} catch (SQLException e) {
			System.out.println("Insert song exception: " + e.getMessage());
			try {
				System.out.println("Performing rollback");
				connection.rollback();
			} catch (SQLException e2) {
				System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
			}
		} finally {
			try {
				System.out.println("Resetting default commit behavior");
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("Couldn't reset auto-commit! " + e.getMessage());
			}

		}
	}

}
