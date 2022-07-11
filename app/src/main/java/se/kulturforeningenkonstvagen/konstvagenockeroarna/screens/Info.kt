package se.kulturforeningenkonstvagen.konstvagenockeroarna.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.kulturforeningenkonstvagen.konstvagenockeroarna.ui.theme.KonstvågenÖckeröarnaTheme

@Composable
fun InfoScreen() {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    KonstvågenÖckeröarnaTheme {
        Surface(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // TITLE
                Text(
                    text = "KONSTVÅGEN",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = "ÖCKERÖ 2022",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Justify
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colors.primary
                )


                // INFO AND DATE
                Text(
                    text = "Besök 110 konstnärer på Öckeröarna",
                    modifier = Modifier.padding(top = 8.dp),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "6 - 7 augusti",
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.h4
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colors.primary
                )

                // LINKS
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = context.resources.getIdentifier(
                            "ic_website",
                            "drawable",
                            context.packageName
                        )),
                        contentDescription = "Website Icon",
                        Modifier
                            .size(32.dp)
                            .padding(end = 8.dp),
                        tint = MaterialTheme.colors.primary
                    )
                    ClickableText(
                        text = AnnotatedString("kulturforeningenkonstvagen.se"),
                        onClick = {
                            uriHandler.openUri("https://www.kulturforeningenkonstvagen.se")
                        },
                        modifier = Modifier.padding(horizontal = 4.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Thin,
                            fontSize = 16.sp,
                            letterSpacing = 0.25.sp
                        )
                    )
                }

                Row(
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = context.resources.getIdentifier(
                            "ic_website",
                            "drawable",
                            context.packageName
                        )),
                        contentDescription = "Website Icon",
                        Modifier
                            .size(32.dp)
                            .padding(end = 8.dp),
                        tint = MaterialTheme.colors.primary
                    )
                    ClickableText(
                        text = AnnotatedString("www.ockero.se/konstvagen"),
                        onClick = {
                            uriHandler.openUri("https://www.ockero.se/konstvagen")
                        },
                        modifier = Modifier.padding(horizontal = 4.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Thin,
                            fontSize = 16.sp,
                            letterSpacing = 0.25.sp
                        )
                    )
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colors.primary
                )


                Text(
                    text = "Samlingsutställning \n11 juni - 7 augusti",
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(
                        text = "Utställningshallen i \nÖckerö Bibliotek Navet",
                        textAlign = TextAlign.Center
                    )


                    Text(
                        text = "Sockenvägen 4, Öckerö",
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                Text(
                    text = "Utställningshallen följer \nbibliotekets öppettider",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(text = "För aktuell information:")

                Row(
                    modifier = Modifier
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = context.resources.getIdentifier(
                            "ic_website",
                            "drawable",
                            context.packageName
                        )),
                        contentDescription = "Website Icon",
                        Modifier
                            .size(32.dp)
                            .padding(end = 8.dp),
                        tint = MaterialTheme.colors.primary
                    )
                    ClickableText(
                        text = AnnotatedString("ockero.se/bibliotek"),
                        onClick = {
                            uriHandler.openUri("https://www.ockero.se/bibliotek")
                        },
                        style = TextStyle(
                            fontWeight = FontWeight.Thin,
                            fontSize = 16.sp,
                            letterSpacing = 0.25.sp
                        )
                    )
                }

                Text(
                    text = "Under Konstvågenhelgen \n6 - 7 augusti \när utställningen öppen \n11 - 16",
                    textAlign = TextAlign.Center
                )


                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colors.primary
                )

                // LOGOS
                Image(painter = painterResource(id = context.resources.getIdentifier(
                    "kulturforeningen",
                    "drawable",
                    context.packageName
                )), contentDescription = "Konstvågens logga")

                Image(
                    painter = painterResource(id = context.resources.getIdentifier(
                    "ockerokommun",
                    "drawable",
                    context.packageName
                )),
                    contentDescription = "Öckerö Kommuns logga",
                    Modifier.padding(vertical = 16.dp)
                )

                Image(
                    painter = painterResource(id = context.resources.getIdentifier(
                        "vgr",
                        "drawable",
                        context.packageName
                    )),
                    contentDescription = "Västra Götalandsregionens logga",
                    Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}