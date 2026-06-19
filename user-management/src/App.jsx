import { BrowserRouter, Routes, Route,NavLink }
    from "react-router-dom";

import UserList from "./components/UserList";
import AddUser from "./components/AddUser";

function App() {

    return (


        <BrowserRouter>

            <nav className="navbar navbar-light bg-light p-3">
                <NavLink
                    to="/users"
                    className="btn btn-primary me-2"
                >
                    User List
                </NavLink>

                <NavLink
                    to="/add-user"
                    className="btn btn-success"
                >
                    Add User
                </NavLink>
            </nav>

            <Routes>

                <Route path="/users" element={<UserList />} />

                <Route path="/add-user" element={<AddUser />} />

            </Routes>

        </BrowserRouter>

    );
}

export default App;