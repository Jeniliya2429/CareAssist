import axios from "axios";
import { useEffect, useState } from "react";

const UserList = () => {

    const [users, setUsers] = useState([]);

    const api = "https://jsonplaceholder.typicode.com/users";

    useEffect(() => {
        const getUsers = async () => {

        try {

            const resp = await axios.get(api);

            setUsers(resp.data);

        }
        catch (err) {

            console.log(err);

        }

    };

        getUsers();

    }, []);

    

    const deleteUser = async (id) => {

        try {

            await axios.delete(api+`/${id}`);

            const updatedUsers = users.filter((user) => user.id !== id);

            setUsers(updatedUsers);

        }
        catch (err) {

            console.log(err);

        }

    };

    return (
        <div className="container mt-4">

            <h2>User List</h2>

            <table className="table table-bordered">

                <thead>

                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Company</th>
                        <th>Action</th>
                    </tr>

                </thead>

                <tbody>

                    {
                        users.map((user,index) => (

                            <tr key={index}>

                                <td>{user.id}</td>
                                <td>{user?.name}</td>
                                <td>{user?.email}</td>
                                <td>{user?.phone}</td>
                                <td>{user?.company?.name}</td>

                                <td>

                                    <button
                                        className="btn btn-danger"
                                        onClick={() => deleteUser(user.id)
                                        }
                                    >
                                        Delete
                                    </button>

                                </td>

                            </tr>

                        ))
                    }

                </tbody>

            </table>

        </div>
    );
};

export default UserList;