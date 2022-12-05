package Ej11_DAO.dao.sql;

import Ej11_DAO.dao.*;

public class SQLiteDAOManagerImpl implements DAOManager {

    private HikariCP connectionPool;

    private EscuderiaDAO escuderias = null;
    private PilotoDAO pilotos = null;

    private CircuitoDAO circuitos = null;

    public SQLiteDAOManagerImpl() {
        connectionPool = new HikariCP();
    }

    /*public void close() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }*/

    @Override
    public EscuderiaDAO getEscuderiaDAO() {
        if (escuderias == null) {
            escuderias = new SQLiteEscuderiaDAOImpl(connectionPool.getDataSource());
        }
        return escuderias;
    }

    @Override
    public PilotoDAO getPilotoDAO() {
        if (pilotos == null) {
            pilotos = new SQLitePilotoDAOImpl(connectionPool.getDataSource());
        }
        return pilotos;
    }

    public CircuitoDAO getCircuitoDao() {
        if (circuitos == null) {
            circuitos = new SQLiteCircuitoDAOImpl(connectionPool.getDataSource());
        }
        return circuitos;
    }
}
