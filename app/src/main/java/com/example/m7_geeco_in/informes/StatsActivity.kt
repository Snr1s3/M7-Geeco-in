package com.example.m7_geeco_in.informes

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.data.geecoinAPI
import com.example.m7_geeco_in.databinding.ActivityStatsBinding
import com.example.m7_geeco_in.screen.MenuAndroid
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class StatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatsBinding
    private var statsSaved:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MenuAndroid(window).hideSystemBar()
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.statstoolbar)
        supportActionBar?.title =InforBoto.nomAplicacio
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.txtTotalTirades.text = (
                InforBoto.estadistica.clicksIngresos +
                        InforBoto.estadistica.clicksDespeses +
                        InforBoto.estadistica.clicksObjectius
                ).toString()

        UpdateBarGraph()
        UpdatePieGraph()
        binding.btnResetStats.setOnClickListener(this::resetStats)
    }

    private fun UpdatePieGraph() {
        val totalClicks = InforBoto.estadistica.clicksIngresos +
                InforBoto.estadistica.clicksDespeses +
                InforBoto.estadistica.clicksObjectius

        if (totalClicks == 0) {
            binding.piechartDobles.clear()
            binding.piechartDobles.invalidate()
            return
        }

        val entries = listOf(
            PieEntry(InforBoto.estadistica.clicksIngresos.toFloat(), "Ingressos"),
            PieEntry(InforBoto.estadistica.clicksDespeses.toFloat(), "Despeses"),
            PieEntry(InforBoto.estadistica.clicksObjectius.toFloat(), "Objectius")
        )

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.colors = listOf(
            Color.rgb(100, 181, 246),
            Color.rgb(255, 138, 128),
            Color.rgb(174, 213, 129)
        )
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTextSize = 16f

        binding.piechartDobles.apply {
            data = PieData(pieDataSet)
            description.isEnabled = false
            isDrawHoleEnabled = true
            holeRadius = 40f
            setHoleColor(Color.WHITE)
            animateY(1000)
            setEntryLabelColor(Color.BLACK)
            setEntryLabelTextSize(14f)
            invalidate()
        }
    }

    private fun UpdateBarGraph() {
        val entries = listOf(
            BarEntry(0f, InforBoto.estadistica.clicksIngresos.toFloat()),
            BarEntry(1f, InforBoto.estadistica.clicksDespeses.toFloat()),
            BarEntry(2f, InforBoto.estadistica.clicksObjectius.toFloat())
        )

        val barDataSet = BarDataSet(entries, "Clics per botó")
        barDataSet.colors = listOf(
            Color.rgb(100, 181, 246), // Blau
            Color.rgb(255, 138, 128), // Vermell clar
            Color.rgb(174, 213, 129)  // Verd clar
        )
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f
        barDataSet.valueFormatter = DefaultValueFormatter(0)

        val barData = BarData(barDataSet)

        binding.barchartDaus.apply {
            data = barData
            xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(listOf("Ingressos", "Despeses", "Objectius"))
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                setDrawGridLines(false)
            }

            axisLeft.setDrawGridLines(false)
            axisRight.isEnabled = false

            description.text = "Clics per botó"
            animateY(1000)
            invalidate()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_estadistica, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menuGuardar)
        {
            Toast.makeText(this, "Estadística guardada correctament", Toast.LENGTH_SHORT).show()

            try {
                (application as InforBoto).saveStats()
                this.statsSaved=true
                Toast.makeText(this, "Estadística guardada correctament", Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                //Toast.makeText(this, "Error:"+e.message, Toast.LENGTH_SHORT).show()
            }
            return true
        }else return super.onOptionsItemSelected(item)
    }

    private fun resetStats(view: View?) {
        (application as InforBoto).resetStats()
        this.statsSaved=false
        UpdateBarGraph()
        UpdatePieGraph()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.menuGuardar)?.setEnabled(!this.statsSaved)
        return super.onPrepareOptionsMenu(menu)
    }
}
