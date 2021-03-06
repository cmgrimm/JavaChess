package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {

    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

    	final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

    	for(int i = 0; i < 64; i++){
    		emptyTileMap.put(i, new EmptyTile(i));
    	}

    	return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece) {
    	return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
    }

    Tile(int tileCoordinate){
      this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile{

        private EmptyTile(final int tileCoordinate){
            super(tileCoordinate);
        }

        //By definition the tile is empty
        @Override
        public boolean isTileOccupied(){
          return false;
        }

        //By definition there is no piece
        @Override
        public Piece getPiece(){
          return null;
        }

    }//end EmptyTile

    public static final class OccupiedTile extends Tile{

        private final Piece pieceOnTile;

        private OccupiedTile(int tileCoordinate, Piece pieceOnTile){
          super(tileCoordinate);
          this.pieceOnTile = pieceOnTile;
        }

        //by definition tile is occupied
        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }

    }//end OccupiedTile

}
