package idv.hzm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hzm.app.mode.Coin;

public interface CoinRepository extends JpaRepository<Coin, String>{}
