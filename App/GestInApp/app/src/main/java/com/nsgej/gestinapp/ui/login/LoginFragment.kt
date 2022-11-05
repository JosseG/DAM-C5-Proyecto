package com.nsgej.gestinapp.ui.login


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentLoginBinding
import com.nsgej.gestinapp.domain.model.*
import com.nsgej.gestinapp.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.awaitAll

@AndroidEntryPoint
class LoginFragment : Fragment() {
    //lo comente  solo para que los demas puedan ver fragment , usted descomentelo es provisional
    /*companion object {
       fun newInstance() = LoginFragment()
   }*/
    private val loginViewModel by viewModels<LoginViewModel>()

    private var _binding: FragmentLoginBinding?= null
    val binding get( )= _binding!!

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container,false)

        sharedPreferences = context?.getSharedPreferences("preferences", Context.MODE_PRIVATE)!!
        return (binding.root)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = binding.txtUsuario
        val password = binding.txtContrasena
        val login = binding.btnIngresar

        login.setOnClickListener {
            loginViewModel.login(username.editText?.text.toString(), password.editText?.text.toString())
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner){
            val loginResult = it ?: return@observe

            if (loginResult.error != null) {
                return@observe
            }
            if (loginResult.success != null) {

                val editor : SharedPreferences.Editor =sharedPreferences.edit()

                editor.putString("ID_EMPLEADO",loginResult.success.idEmpleado)
                editor.apply()

                findNavController().navigate(R.id.action_loginFragment_to_bienvenidoFragment)
            }

        }

        loginViewModel.usuarioMutable.observe(viewLifecycleOwner){
            for(usuario in it){
                /*Log.i("Usuario",usuario.alias)*/
            }
        }

        /* ------------------------------------- 1 COMENTADO --------------------------------------*/
        val sucursal = Sucursal("S00001","NOR-ESTE",true)

        val cargo = Cargo("C00001","ADMINISTRADOR",true)

        val almacen = Almacen("A0001","S00001","Almacen-A","Lurin",true)

        val empleado = Empleado("E00001","A0001","Jhon","Martinez","jmartines@email.com","990260333",true)

        val usuario = Usuario(idEmpleado = "E00001", idCargo = "C00001", alias = "vendedor", contrasena = "vendedor", estado = true)

        val sucursales = listOf(
            sucursal,
            Sucursal("S00002","NOR-OESTE",true)
        )

        val almacenes = listOf(
            almacen
        )

        val empleados = listOf(
            empleado
        )

        val usuarios = listOf(
            usuario
        )
        val cargos = listOf(
            cargo,
            Cargo("C00002","VENDEDOR",true)
        )

        loginViewModel.insertarCargos(cargos)
        loginViewModel.insertarSucursales(sucursales)

        loginViewModel.sucursalesMutable.observe(viewLifecycleOwner){
            loginViewModel.obtenerSucursal(sucursal.id)
        }

        loginViewModel.sucursalObtenido.observe(viewLifecycleOwner){
            loginViewModel.insertarAlmacenesPorSucursal(almacenes,sucursal)
            loginViewModel.obtenerCargo(cargo.id)
        }

        loginViewModel.cargoObtenido.observe(viewLifecycleOwner){
            loginViewModel.insertarEmpleadosPorAlmacen(empleados,almacen)
            loginViewModel.insertarUsuariosPorEmpleadosPorCargo(usuarios,empleados,it)
        }

        /* ------------------------------------- 1 COMENTADO --------------------------------------*/

        /* ------------------------------------- 2 COMENTADO --------------------------------------*/

/*        val empleado = Empleado("E00002","A0001","Ximena","Perez","xperez@email.com","990260000",true)

        val usuario = Usuario(idEmpleado = "E00002", idCargo = "C00001", alias = "admin", contrasena = "admin", estado = true)
        val empleados = listOf(
            empleado
        )

        val usuarios = listOf(
            usuario
        )*/


/*        val almacen = Almacen("A0001","S00002","Almacen-A","Lurin",true)

        loginViewModel.obtenerCargo("C00001")

        loginViewModel.cargoObtenido.observe(viewLifecycleOwner){
            loginViewModel.insertarEmpleadosPorAlmacen(empleados,almacen)
            loginViewModel.insertarUsuariosPorEmpleadosPorCargo(usuarios,empleados,it)
        }*/



        /* ------------------------------------- 2 COMENTADO --------------------------------------*/


    }


    override fun onDestroy(){
        super.onDestroy()
        _binding=null
    }

}

