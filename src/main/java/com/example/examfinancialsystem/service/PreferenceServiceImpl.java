package com.example.examfinancialsystem.service;

import com.example.examfinancialsystem.dto.AddPreferenceRequest;
import com.example.examfinancialsystem.dto.PreferenceDTO;
import com.example.examfinancialsystem.dto.UserPreferenceResponse;
import com.example.examfinancialsystem.dto.UpdatePreferenceRequest;
import com.example.examfinancialsystem.entity.LikeList;
import com.example.examfinancialsystem.entity.Product;
import com.example.examfinancialsystem.entity.User;
import com.example.examfinancialsystem.repository.LikeListRepository;
import com.example.examfinancialsystem.repository.ProductRepository;
import com.example.examfinancialsystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PreferenceServiceImpl implements PreferenceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LikeListRepository likeListRepository;

    @Override
    @Transactional // 確保整個方法在一個交易中執行，出錯會全部回滾
    public void addPreference(AddPreferenceRequest request) {
        // 1. 驗證產品是否存在
        Product product = productRepository.findById(request.getProductNo())
                .orElseThrow(() -> new EntityNotFoundException("找不到產品編號: " + request.getProductNo()));

        // 2. 進行費用計算 (使用 BigDecimal 確保精度)
        BigDecimal quantity = new BigDecimal(request.getQuantity());
        BigDecimal totalValue = product.getPrice().multiply(quantity);
        BigDecimal totalFee = totalValue.multiply(product.getFeeRate());
        BigDecimal totalAmount = totalValue.add(totalFee);

        // 3. 建立新的 LikeList 實體
        LikeList newPreference = new LikeList();
        newPreference.setUserId(request.getUserId());
        newPreference.setProductNo(request.getProductNo());
        newPreference.setOrderName(request.getQuantity()); // OrderName 存放數量
        newPreference.setAccount(request.getPurchaseAccount());
        newPreference.setTotalFee(totalFee);
        newPreference.setTotalAmount(totalAmount);

        // 4. 儲存到資料庫
        likeListRepository.save(newPreference);
    }

    @Override
    public UserPreferenceResponse getUserPreferences(String userId) {
        // 1. 驗證使用者是否存在並取得 Email
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("找不到使用者ID: " + userId));

        // 2. 查詢該使用者的所有喜好清單
        List<LikeList> preferences = likeListRepository.findByUserId(userId);

        // 3. 組合回應的 DTO
        UserPreferenceResponse response = new UserPreferenceResponse();
        response.setUserEmail(user.getEmail());

        List<PreferenceDTO> preferenceDTOs = new ArrayList<>();
        for (LikeList pref : preferences) {
            // 為了取得產品名稱，需要再次查詢 Product 表
            Product product = productRepository.findById(pref.getProductNo()).orElse(new Product()); // 如果產品被刪除，給一個空物件避免錯誤

            PreferenceDTO dto = new PreferenceDTO();
            dto.setSn(pref.getSn());
            dto.setProductName(product.getProductName());
            dto.setAccount(pref.getAccount());
            dto.setTotalFee(pref.getTotalFee());
            dto.setTotalAmount(pref.getTotalAmount());
            preferenceDTOs.add(dto);
        }
        response.setPreferences(preferenceDTOs);

        return response;
    }

    @Override
    @Transactional
    public void updatePreference(Long sn, UpdatePreferenceRequest request) {
        // 1. 驗證該筆喜好紀錄是否存在
        LikeList preferenceToUpdate = likeListRepository.findById(sn)
                .orElseThrow(() -> new EntityNotFoundException("找不到喜好紀錄 SN: " + sn));

        // 2. 找到關聯的產品以重新計算費用
        Product product = productRepository.findById(preferenceToUpdate.getProductNo())
                .orElseThrow(() -> new EntityNotFoundException("找不到關聯的產品編號: " + preferenceToUpdate.getProductNo()));

        // 3. 根據新的數量重新計算費用
        BigDecimal quantity = new BigDecimal(request.getQuantity());
        BigDecimal totalValue = product.getPrice().multiply(quantity);
        BigDecimal totalFee = totalValue.multiply(product.getFeeRate());
        BigDecimal totalAmount = totalValue.add(totalFee);

        // 4. 更新紀錄的欄位
        preferenceToUpdate.setOrderName(request.getQuantity()); // 更新數量
        preferenceToUpdate.setAccount(request.getPurchaseAccount()); // 更新帳號
        preferenceToUpdate.setTotalFee(totalFee); // 更新總手續費
        preferenceToUpdate.setTotalAmount(totalAmount); // 更新總金額

        // 5. 儲存更新後的紀錄 (JPA 會自動執行 UPDATE)
        likeListRepository.save(preferenceToUpdate);
    }

    @Override
    @Transactional
    public void deletePreference(Long sn) {
        // 1. 驗證紀錄是否存在，不存在會拋出例外
        if (!likeListRepository.existsById(sn)) {
            throw new EntityNotFoundException("找不到喜好紀錄 SN: " + sn);
        }
        // 2. 執行刪除
        likeListRepository.deleteById(sn);
    }
}