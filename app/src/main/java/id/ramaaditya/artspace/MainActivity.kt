package id.ramaaditya.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ramaaditya.artspace.ui.theme.ArtSpaceTheme

// MainActivity: Komponen utama aplikasi yang mengimplementasikan UI menggunakan Jetpack Compose.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Mengatur tema dan memanggil fungsi utama aplikasi.
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

// ArtSpaceApp: Fungsi utama untuk mendefinisikan UI aplikasi, termasuk gambar dan tombol navigasi.
@Composable
fun ArtSpaceApp() {
    // 'position' menyimpan status gambar yang sedang ditampilkan.
    var position by remember { mutableStateOf(1) }

    // Memilih resource gambar berdasarkan posisi.
    val imageRes = when (position) {
        1 -> R.drawable.villeneuve
        2 -> R.drawable.starrynight
        3 -> R.drawable.persistencememory
        else -> R.drawable.ic_launcher_background
    }

    // Memilih string untuk judul karya seni berdasarkan posisi.
    val artworkTitle = when {
        position == 1 -> R.string.artwork1
        position == 2 -> R.string.artwork2
        position == 3 -> R.string.artwork3
        else -> R.string.artwork1
    }

    // Memilih string untuk nama artis berdasarkan posisi.
    val artworkArtist = when {
        position == 1 -> R.string.artist1
        position == 2 -> R.string.artist2
        position == 3 -> R.string.artist3
        else -> R.string.artist1
    }

    // Kolom vertikal untuk menampilkan gambar, teks judul, dan artis.
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bagian untuk menampilkan gambar dengan bayangan dan border.
        Column(
            modifier = Modifier
                .size(height = 500.dp, width = 350.dp)
                .shadow(12.dp, RectangleShape)
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(30.dp, SolidColor(Color.White)), RectangleShape)
                    .shadow(12.dp, RectangleShape)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Bagian untuk menampilkan judul karya seni dan artis.
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(300.dp, 100.dp)
                .scale(1f)
                .padding(15.dp)
                .background(Color.LightGray)
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = stringResource(artworkTitle),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(artworkArtist),
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Bagian untuk menampilkan tombol Previous dan Next.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier= Modifier.size(width = 150.dp, height = 50.dp),
                onClick = {
                    position = when (position) {
                        1 -> 1 // Jika posisi sudah di awal, tidak bisa kembali lebih jauh.
                        else -> --position // Mengurangi posisi untuk Previous.
                    }
                }) {
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.width(50.dp))

            Button(
                modifier= Modifier.size(width = 150.dp, height = 50.dp),
                onClick = {
                    position = when (position) {
                        in 1..2 -> ++position // Menambah posisi untuk Next.
                        else -> 1
                    }
                }) {
                Text(text = "Next")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
