import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';


export default function Modal() {

    const [showModal, setShowModal] = useState(false);
    const [formData, setFormData] = useState({ name: '' });

    const handleChange = (e) => {
        setFormData(prev => ({
            ...prev,
            [e.target.name]: e.target.value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("valores ingresados", formData)
        setShowModal(false);
    }

    return (
        <div>

            {showModal && (
                <div style={styles.modalOverlay}>
                    <div style={styles.modalContent}>
                        <h2>Ingresar datos</h2>
                        <form onSubmit={handleSubmit}>
                            <input
                                name="name"
                                placeholder="Nombre"
                                value={formData.name}
                                onChange={handleChange}
                            /><br />
                            <input
                                name="price"
                                placeholder="Precio"
                                value={formData.price}
                                onChange={handleChange}
                            /><br />
                            <button type="submit">Guardar</button>
                            <button type="button" onClick={() => setShowModal(false)}>Cancelar</button>
                        </form>
                    </div>
                </div>
            )}
        </div>
    )
}