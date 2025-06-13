import {Routes, Route, BrowserRouter} from 'react-router-dom';

import Put from '../put/Put';
import App from '../../App';
import Post from '../post/Post'
 

export default function Router() {
    return(
        <BrowserRouter>
            <Routes>
            <Route path="/:id" element={<Put/>}/>
            <Route path='/' element={<App/>} />
            <Route path='/crear' element={<Post/>} />
            </Routes>
        </BrowserRouter>
    )
}