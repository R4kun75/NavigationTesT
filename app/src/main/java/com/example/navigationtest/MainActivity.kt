package com.example.navegacaoexemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavHostController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "telaA") {
        composable("telaA") {
            TelaA(navController)
        }

        composable(
            route = "telaB/{nome}",
            arguments = listOf(navArgument("nome") { type = NavType.StringType })
        ) { backStackEntry ->
            val nome = backStackEntry.arguments?.getString("nome")
            TelaB(nome)
        }
    }
}

@Composable
fun TelaA(navController: NavHostController) {
    var nome by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Digite seu nome:", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nome,
            onValueChange = { nome = it },
            placeholder = { Text("Seu nome aqui") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nome.isNotEmpty()) {
                navController.navigate("telaB/$nome")
            }
        }) {
            Text("Entrar")
        }
    }
}

@Composable
fun TelaB(nome: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Bem-vindo, ${nome ?: "usuário"}!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

