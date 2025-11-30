package com.example.flashpedition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flashpedition.entity.Alamat;
import com.example.flashpedition.entity.Kendaraan;
import com.example.flashpedition.entity.Kurir;
import com.example.flashpedition.entity.Logistik;
import com.example.flashpedition.entity.Paket;
import com.example.flashpedition.entity.Penerima;
import com.example.flashpedition.repository.AlamatRepository;
import com.example.flashpedition.repository.BarangRepository;
import com.example.flashpedition.repository.KendaraanRepository;
import com.example.flashpedition.repository.KurirRepository;
import com.example.flashpedition.repository.PaketRepository;
import com.example.flashpedition.repository.PembayaranRepository;
import com.example.flashpedition.repository.PenerimaRepository;
import com.example.flashpedition.repository.PengirimanRepository;
import com.example.flashpedition.repository.SupplierRepository;
import com.example.flashpedition.service.PermintaanRestockService;

/**
 * Controller responsible for serving Thymeleaf page templates.
 * Keeps REST API separate from view layer (see {@link PengirimanController}).
 */
@Controller
public class PageController {

    private final PengirimanRepository pengirimanRepository;
    private final AlamatRepository alamatRepository;
    private final KurirRepository kurirRepository;
    private final KendaraanRepository kendaraanRepository;
    private final PenerimaRepository penerimaRepository;
    private final SupplierRepository supplierRepository;
    private final PaketRepository paketRepository;
    private final PembayaranRepository pembayaranRepository;
    private final BarangRepository barangRepository;
    private final PermintaanRestockService permintaanRestockService;

    public PageController(PengirimanRepository pengirimanRepository,
            AlamatRepository alamatRepository,
            KurirRepository kurirRepository,
            KendaraanRepository kendaraanRepository,
            PenerimaRepository penerimaRepository,
            SupplierRepository supplierRepository,
            PaketRepository paketRepository,
            PembayaranRepository pembayaranRepository,
            BarangRepository barangRepository,
            PermintaanRestockService permintaanRestockService) {
        this.pengirimanRepository = pengirimanRepository;
        this.alamatRepository = alamatRepository;
        this.kurirRepository = kurirRepository;
        this.kendaraanRepository = kendaraanRepository;
        this.penerimaRepository = penerimaRepository;
        this.supplierRepository = supplierRepository;
        this.paketRepository = paketRepository;
        this.pembayaranRepository = pembayaranRepository;
        this.barangRepository = barangRepository;
        this.permintaanRestockService = permintaanRestockService;
    }

    @GetMapping("/")
    public String pilihLogin() {
        return "pilih_login";
    }

    @GetMapping("/login/buyer")
    public String loginBuyer() {
        return "login_buyer";
    }

    @GetMapping("/login/seller")
    public String loginSeller() {
        return "login_seller";
    }

    @GetMapping("/login/supplier")
    public String loginSupplier() {
        return "login_supplier";
    }

    @GetMapping("/login/logistic")
    public String loginLogistic() {
        return "login_logistic";
    }

    @GetMapping("/register/buyer")
    public String showRegisterBuyer() {
        return "register_buyer";
    }

    @GetMapping("/register/seller")
    public String showRegisterSeller() {
        return "register_seller";
    }

    @GetMapping("/register/supplier")
    public String showRegisterSupplier() {
        return "register_supplier";
    }

    @GetMapping("/register/logistic")
    public String showRegisterLogistic() {
        return "register_logistic";
    }

    @GetMapping("/menu_buyer")
    public String menuBuyer() {
        return "menu_buyer";
    }

    @GetMapping("/menu_seller")
    public String menuSeller() {
        return "menu_seller";
    }

    @GetMapping("/menu_supplier")
    public String menuSupplier() {
        return "menu_supplier";
    }

    @GetMapping("/menu_logistic")
    public String menuLogistic() {
        return "menu_logistic";
    }

    @GetMapping("/buyer/resi")
    public String showBuyerResiForm() {
        return "buyer_resi";
    }

    @PostMapping("/buyer/resi")
    public String handleBuyerResiSearch(@RequestParam String resi, Model model) {
        return pengirimanRepository.findLogistikByResi(resi)
                .map(p -> {
                    model.addAttribute("pengiriman", p);
                    return "buyer_resi_result";
                })
                .orElseGet(() -> {
                    model.addAttribute("notFound", true);
                    return "buyer_resi";
                });
    }

    @GetMapping("/buyer/history")
    public String showBuyerHistory(Model model) {
        model.addAttribute("pengirimanList", pengirimanRepository.findAllLogistik());
        return "buyer_history";
    }

    // Seller - create simple Logistik shipment
    @GetMapping("/seller/pengiriman/new")
    public String showNewPengirimanForm() {
        return "seller_new_pengiriman";
    }

    @PostMapping("/seller/pengiriman/new")
    public String createNewPengiriman(
            @RequestParam String resi,
            @RequestParam double volume,
            @RequestParam String alamatAsal,
            @RequestParam String alamatTujuan,
            @RequestParam(required = false) String kecamatanAsal,
            @RequestParam(required = false) String kelurahanAsal,
            @RequestParam(required = false) String kotaAsal,
            @RequestParam(required = false) String provinsiAsal,
            @RequestParam(required = false) String kodePosAsal,
            @RequestParam(required = false) String kecamatanTujuan,
            @RequestParam(required = false) String kelurahanTujuan,
            @RequestParam(required = false) String kotaTujuan,
            @RequestParam(required = false) String provinsiTujuan,
            @RequestParam(required = false) String kodePosTujuan,
            @RequestParam String idKurir,
            @RequestParam String statusKurir,
            @RequestParam String platNomor,
            @RequestParam(required = false) String merk,
            @RequestParam(required = false) String model,
            @RequestParam String idPenerima,
            @RequestParam double berat,
            @RequestParam double ukuran,
            @RequestParam String fragile,
            @RequestParam(required = false) String statusPaket,
            // Payment fields
            @RequestParam String id_pembayaran,
            @RequestParam double jumlah,
            @RequestParam String metode,
            @RequestParam String statusPembayaran,
            @RequestParam(required = false) String tanggal_bayar) {

        Alamat asal = new Alamat();
        asal.setJalan(alamatAsal);
        asal.setKecamatan(kecamatanAsal);
        asal.setKelurahan(kelurahanAsal);
        asal.setKota(kotaAsal);
        asal.setProvinsi(provinsiAsal);
        asal.setKodePos(kodePosAsal);
        alamatRepository.save(asal);

        Alamat tujuan = new Alamat();
        tujuan.setJalan(alamatTujuan);
        tujuan.setKecamatan(kecamatanTujuan);
        tujuan.setKelurahan(kelurahanTujuan);
        tujuan.setKota(kotaTujuan);
        tujuan.setProvinsi(provinsiTujuan);
        tujuan.setKodePos(kodePosTujuan);
        alamatRepository.save(tujuan);

        Kendaraan kendaraan;
        Kurir kurir = kurirRepository.findByIdKurir(idKurir).orElse(null);

        if (kurir != null && kurir.getKendaraan() != null) {
            // nothing to do: kurir already has its kendaraan
        } else {
            kendaraan = new Kendaraan();
            kendaraan.setPlatNomor(platNomor);
            if (merk != null && !merk.isBlank()) {
                kendaraan.setMerk(merk);
            }
            if (model != null && !model.isBlank()) {
                kendaraan.setModel(model);
            }
            kendaraan = kendaraanRepository.save(kendaraan);

            if (kurir == null) {
                kurir = new Kurir();
                kurir.setIdKurir(idKurir);
            }
            kurir.setKendaraan(kendaraan);
        }

        kurir.setStatus(statusKurir);
        kurirRepository.save(kurir);

        Penerima penerima = penerimaRepository.findByIdPenerima(idPenerima);
        if (penerima == null) {
            penerima = new Penerima();
            penerima.setIdPenerima(idPenerima);
            penerima = penerimaRepository.save(penerima);
        }

        // pick some existing supplier, e.g. by username or by id
        // example: use the first one (you already have one row)
        var supplier = supplierRepository.findAll().stream().findFirst().orElse(null);

        Logistik pengiriman = new Logistik();
        pengiriman.setResi(resi);
        pengiriman.setVolume(volume);
        pengiriman.setAlamatAsal(asal);
        pengiriman.setAlamatTujuan(tujuan);
        pengiriman.setKurir(kurir.getIdKurir());
        pengiriman.setPenerimaObj(penerima);

        if (supplier != null) {
            pengiriman.setSupplier(supplier);
        }

        pengirimanRepository.save(pengiriman);

        // Create or update Pembayaran
        com.example.flashpedition.entity.Pembayaran pembayaran = pembayaranRepository.findByIdPembayaran(id_pembayaran);
        if (pembayaran == null) {
            pembayaran = new com.example.flashpedition.entity.Pembayaran();
            pembayaran.setIdPembayaran(id_pembayaran);
        }
        pembayaran.setJumlah(jumlah);
        pembayaran.setMetode(metode);
        pembayaran.setStatus(statusPembayaran);
        if (tanggal_bayar != null && !tanggal_bayar.isBlank()) {
            pembayaran.setTanggalBayar(tanggal_bayar);
        } else {
            pembayaran.setTanggalBayar("");
        }
        pembayaran.setPengiriman(pengiriman);
        pembayaranRepository.save(pembayaran);

        String paketStatus = (statusPaket != null && !statusPaket.isBlank())
                ? statusPaket
                : "Diproses";

        Paket paket = new Paket(
                resi,
                supplier, // null is allowed if you don't set supplier yet
                penerima,
                kurir.getIdKurir(),
                asal,
                tujuan,
                berat,
                ukuran,
                paketStatus,
                fragile);
        paketRepository.save(paket);

        return "redirect:/menu_seller";
    }

    // Supplier - add new Barang
    @PostMapping("/supplier/stok-barang/tambah")
    public String tambahBarang(
            @RequestParam("id_barang") String id_barang,
            @RequestParam("nama_barang") String nama_barang,
            @RequestParam("jenis_barang") String jenis_barang,
            @RequestParam("jumlah") int jumlah,
            @RequestParam("satuan") String satuan,
            Model model) {
        com.example.flashpedition.entity.Barang barang = new com.example.flashpedition.entity.Barang(
                id_barang, nama_barang, jenis_barang, jumlah, satuan);
        barangRepository.save(barang);
        // Redirect to stock page to show updated list
        return "redirect:/supplier/stok-barang";
    }

    @PostMapping("/supplier/permintaan-restock")
    public String ajukanPermintaanRestock(@RequestParam("id_barang") @org.springframework.lang.NonNull String id_barang,
            @RequestParam("jumlah") int jumlah) {
        var barangOpt = barangRepository.findById(id_barang);
        if (barangOpt.isPresent()) {
            var barang = barangOpt.get();
            var pr = new com.example.flashpedition.entity.PermintaanRestock(
                    barang, jumlah, java.time.LocalDateTime.now(), "Diajukan");
            permintaanRestockService.save(pr);
        }
        return "redirect:/supplier/permintaan-restock";
    }

    // Seller - list ongoing orders (for now, all shipments)
    @GetMapping("/seller/orders")
    public String listSellerOrders(Model model) {
        model.addAttribute("pengirimanList", pengirimanRepository.findAllLogistik());
        return "seller_orders";
    }

    // Seller - simple sales report (count + total volume)
    @GetMapping("/seller/report")
    public String sellerReport(Model model) {
        var pengirimanList = pengirimanRepository.findAllLogistik();
        long totalPengiriman = pengirimanList.size();
        double totalVolume = pengirimanList.stream()
                .mapToDouble(Logistik::getVolume)
                .sum();

        model.addAttribute("totalPengiriman", totalPengiriman);
        model.addAttribute("totalVolume", totalVolume);
        return "seller_report";
    }
    // After successful login you can later redirect to menus:
    // e.g. return "menu_buyer"; etc.

    // Supplier - distribution report
    @GetMapping("/supplier/laporan-distribusi")
    public String supplierLaporanDistribusi(Model model) {
        // For demo: show all shipments. In real app, filter by logged-in supplier.
        model.addAttribute("pengirimanList", pengirimanRepository.findAllLogistik());
        return "supplier_laporan_distribusi";
    }

    // Supplier - stock page (using Barang)
    @GetMapping("/supplier/stok-barang")
    public String supplierStokBarang(Model model) {
        model.addAttribute("barangList", barangRepository.findAll());
        return "supplier_stok_barang";
    }

    // Supplier - restock request page
    @GetMapping("/supplier/permintaan-restock")
    public String supplierPermintaanRestock(Model model) {
        model.addAttribute("barangList", barangRepository.findAll());
        model.addAttribute("permintaanRestockList", permintaanRestockService.getAll());
        return "supplier_permintaan_restock";
    }
}