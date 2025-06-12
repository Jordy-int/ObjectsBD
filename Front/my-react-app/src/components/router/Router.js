import {Routes, Route, BrowserRouter} from 'react-router-dom';

import Put from '../put/Put';
import App from '../../App';
 

export default function Router() {
    return(
        <BrowserRouter>
            <Routes>
            <Route path="put/:id" element={<Put/>}/>
            <Route path='/' element={<App/>} ></Route>
            </Routes>
        </BrowserRouter>
    )
}