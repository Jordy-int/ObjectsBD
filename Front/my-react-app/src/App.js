import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useEffect, useState } from 'react';



export default function App() {

  const [objects, setObjects] = useState([]);

  const fetchObjects = async () => {
    try {
      const response = await fetch('http://localhost:8080/objects', {
        method: 'GET'
      })
      const data = await response.json();
      setObjects(data);
    } catch (error) {
      console.log("Error fetch objetos")
    }
  }

  // Ejecuta al momento de cargar el Componente
  useEffect(() => {
    fetchObjects();
  }, []);

  return (
    <>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Id</TableCell>
              <TableCell align="right">Nombre</TableCell>
              <TableCell align="left">Data</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {objects.map((object) => (
              <TableRow
                key={object.id}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {object.id}
                </TableCell>
                <TableCell align="right">{object.name}</TableCell>
                <TableCell>
                  {object.data && typeof object.data === 'object' ? (
                    <ul>
                      {Object.entries(object.data).map(([key, value]) => (
                        <li key={key}>
                          <strong>{key}:</strong> {value}
                        </li>
                      ))}
                    </ul>
                  ) : (
                    'Sin datos'
                  )}
                </TableCell>

                <TableCell><button value={object.id} >Editar  </button></TableCell>
                <TableCell><button value={object.id} >Actualizar</button></TableCell>
                <TableCell><button value={object.id} >Eliminar</button></TableCell>

              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
}