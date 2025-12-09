package es.fpsumma.dam2.utilidades.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.fpsumma.dam2.utilidades.ui.screens.home.HomeScreen
import es.fpsumma.dam2.utilidades.ui.screens.notas.NotasScreen
import es.fpsumma.dam2.utilidades.ui.screens.tareas.ListadoTareasScreen
import es.fpsumma.dam2.utilidades.ui.viewmodel.NotasViewModel
import es.fpsumma.dam2.utilidades.ui.viewmodel.TareasViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    tareasViewModel: TareasViewModel,
    notasViewModel: NotasViewModel
) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) { HomeScreen(navController)}
        composable(Routes.LISTADO_TAREAS) { ListadoTareasScreen(navController,tareasViewModel) }
        composable(Routes.ASIGNATURA_NOTA) { NotasScreen(navController,notasViewModel) }
    }
}