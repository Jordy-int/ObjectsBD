import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import Modal from './Components/Modal';





function createData(id, name, data) {
  return { id, name, data };
}

let rows = [];

let info = [];

export default function BasicTable() {

  fetch('http://localhost:8080/objects', {
    method: 'GET'
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Error en la solicitud');
      }
      return response.json();
    })
    .then(data => {
      rows = [];
      data.forEach(p =>
        rows.push(createData(p.id, p.name, p.data))
      );
    })
    .catch(error => {
      console.error('Error:', error);
    });


  return (
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
          {rows.map((row) => (
            <TableRow
              key={row.id}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.id}
              </TableCell>
              <TableCell align="right">{row.name}</TableCell>
              <TableCell>
                {row.data && typeof row.data === 'object' ? (
                  <ul>
                    {Object.entries(row.data).map(([key, value]) => (
                      <li key={key}>

                        <strong>{key}:</strong> {value}
                      </li>
                    ))}
                  </ul>
                ) : (
                  'Sin datos'
                )}
              </TableCell>
             
              <TableCell><button value={row.id} >Editar</button></TableCell>
              <TableCell><button value={row.id} >Actualizar</button></TableCell>
              <TableCell><button value={row.id} >Eliminar</button></TableCell>

            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

