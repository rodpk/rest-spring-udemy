import React, { useState} from 'react';
import { useNavigate } from 'react-router-dom';
import './styles.css';

import api from '../../services/api'

import logoImage from '../../assets/logo.svg'
import padlock from '../../assets/padlock.png'

export default function Login() {   
    
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    async function login(e) {
        e.preventDefault();

        const data  = {
            username,
            password,
        };

        console.log("HELLO")

        try {
            const response = await api.post('/auth/signin', data);
            localStorage.setItem('username', username);
            localStorage.setItem('accessToken', response.data.token);

            navigate('/books')
        } catch(err) {
            alert('Login failed, try again! ' + err);
        }
    };



    return (
        <div className="login-container">
            <section className="form">
                <img src={logoImage} alt="Logo" />

                <form onSubmit={login}>
                    <h1>Access your account</h1>
                    <input 
                        value={username}
                        onChange={ e => setUsername(e.target.value)}
                        placeholder = "Username" />
                    <input 
                        value={password}
                        onChange={ e => setPassword(e.target.value)}
                        type  =  "password" placeholder = "Password" />
                    <button className = "button" type="submit">Login</button>
                </form>

            </section>

            <img src={padlock} alt="Login" />

        </div>

    )
}