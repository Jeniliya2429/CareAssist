
import { useEffect, useState } from "react"
import {useDispatch} from "react-redux"
import { getAllData } from "../store/action/characterAction"
import {useSelector} from "react-redux"
const CharacterList = () => {


    const [currentPage, setPage] = useState(1)
    const [arry, setArry] = useState([])
    const dispatch = useDispatch()


    useEffect(() => {

        dispatch(getAllData(currentPage))
        setArry( Array.from({ length: totalPages }) )        

    }, [currentPage,totalPages])
    const { characters, totalPages} = useSelector( state => state.character )

    return (

        <div className="container mt-4">

            <h2>
                Characters List
            </h2>

            <table className="table table-bordered">

                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Status</th>
                        <th>Species</th>
                        <th>Origin</th>
                        <th>Location</th>
                    </tr>
                </thead>

                <tbody>
                    {
                        characters.map((c) => (
                            <tr key={c.id}>
                                <td>{c.name}</td>
                                <td>{c.status}</td>
                                <td>{c.species}</td>
                                <td>{c.origin?.name}</td>
                                <td>{c.location?.name}</td>
                            </tr>
                        ))
                    }

                </tbody>

            </table>

            <nav>

                <ul className="pagination justify-content-center">

                    <li className="page-item">

                        <button className="page-link" disabled={currentPage === 1}
                            onClick={() => setPage(currentPage - 1) } >
                            Previous
                        </button>
                    </li>
                    {
                        arry.map((_, index) => (
                            <li
                                className="page-item" key={index} >

                                <button className="page-link"
                                 onClick={() => setPage(index + 1) } >
                                    {index + 1}
                                </button>
                            </li>

                        ))
                    }
                    <li className="page-item">
                        <button className="page-link"
                            disabled={ currentPage === totalPages}
                            onClick={() => setPage(currentPage + 1) } >
                            Next
                        </button>
                    </li>

                </ul>

            </nav>

        </div>
    )
}

export default CharacterList